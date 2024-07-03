using FitAppServer.Models;
using Microsoft.AspNetCore.Http;
using Microsoft.AspNetCore.Mvc;

namespace FitAppServer.Controllers
{
    [Route("api/[controller]")]
    [ApiController]
    public class UserRoleController : ControllerBase
    {
        private ApplicationDBContext _context;

        public UserRoleController(ApplicationDBContext context)
        {
            _context = context;
        }

        [HttpGet("all")]
        public async Task<IActionResult> GetAllUserRolees()
        {
            List<UserRole> roles = _context.UserRoles.ToList();
            return Ok(roles);
        }
    }
}
