using AutoMapper;
using FitAppServer.DTOs;
using FitAppServer.Models;
using Microsoft.AspNetCore.Http;
using Microsoft.AspNetCore.Mvc;
using Microsoft.EntityFrameworkCore;
using Microsoft.IdentityModel.Tokens;
using Microsoft.OpenApi.Validations;
using System.IdentityModel.Tokens.Jwt;
using System.Security.Claims;
using System.Text;

namespace FitAppServer.Controllers
{
    [Route("api/[controller]")]
    [ApiController]
    public class AuthController : ControllerBase
    {
        private IConfiguration _config;
        private IMapper _mapper;
        private ApplicationDBContext _context;

        public AuthController(IConfiguration config, IMapper mapper, ApplicationDBContext context)
        {
            _config = config;
            _context = context;
            _mapper = mapper;
        }

        
        [HttpPost("register")]
        public async Task<IActionResult> Register([FromBody] UserRegisterDTO registerDTO)
        {
            try
            {
                User registeredUser = null;
                // Checks if there's user with the same information
                registeredUser = _context.Users.Include(u => u.UserRole).FirstOrDefault(u => u.UserName == registerDTO.UserName);
                if (registeredUser != null)
                {
                    return BadRequest(new { message = "Username has already existed" });
                }

                registeredUser = _context.Users.Include(u => u.UserRole).FirstOrDefault(u => u.Email == registerDTO.Email);
                if (registeredUser != null)
                {
                    return BadRequest(new { message = "Email has already existed" });
                }

                User user = _mapper.Map<User>(registerDTO);
                _context.Users.Add(user);
                _context.SaveChanges();
                registeredUser = _context.Users.Include(u => u.UserRole).FirstOrDefault(u => u.UserName == registerDTO.UserName && u.Password == registerDTO.Password);
                var token = GenerateJwtToken(registeredUser);
                return Ok(new
                {
                    Message = "User created successfully",
                    Token = token
                });
            }
            catch (Exception ex)
            {
                return BadRequest(new { message = ex.Message });
            }
        }
        

        [HttpPost("login")]
        public async Task<IActionResult> Login([FromBody] UserLoginDTO loginDTO)
        {
            try
            {
                var registeredUser = _context.Users.Include(u => u.UserRole).FirstOrDefault(u => u.UserName == loginDTO.UserName && u.Password == loginDTO.Password);
                if (registeredUser == null)
                {
                    return NotFound(new { message = "User or password is incorrect" });
                }
                var token = GenerateJwtToken(registeredUser);
                return Ok(new { Token = token });
                
            }
            catch (Exception ex)
            {
                return BadRequest(new { message = ex.Message });
            }
        }
        /*
        [HttpPost("logout")]
        public async Task<IActionResult> Logout(User student)
        {
            try
            {
                var registeredStudent = await _userRepository.RegisterStudentAsync(student);
                return Ok(new { Token = token});
            }
            catch (Exception ex)
            {
                return BadRequest(new { message = ex.Message });
            }
        }
        */

        private string GenerateJwtToken(User user)
        {
            var secretKey = _config["Jwt:Key"];
            var issuer = _config["Jwt:Issuer"];
            var audience = _config["Jwt:Audience"];
            var expirationMinutes = Convert.ToInt64(_config["Jwt:ExpirationMinutes"]);

            var securityKey = new SymmetricSecurityKey(Encoding.UTF8.GetBytes(secretKey));
            var credentials = new SigningCredentials(securityKey, SecurityAlgorithms.HmacSha256);

            var claims = new[]
            {
                new Claim(ClaimTypes.NameIdentifier, user.UserId.ToString()),
                new Claim(ClaimTypes.Name, user.UserName),
                new Claim(ClaimTypes.Role, user.UserRole.RoleName)  // Assuming UserRole is loaded
                // Add additional claims as needed (e.g., roles, custom claims)
            };

            var token = new JwtSecurityToken(
                issuer: issuer,
                audience: audience,
                claims: claims,
                expires: DateTime.UtcNow.AddMinutes(expirationMinutes),
                signingCredentials: credentials
            );

            return new JwtSecurityTokenHandler().WriteToken(token);
        }
    }
}
