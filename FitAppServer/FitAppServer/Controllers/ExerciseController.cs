using AutoMapper;
using FitAppServer.DTOs;
using FitAppServer.Models;
using Microsoft.AspNetCore.Mvc;
using System.Globalization;

namespace FitAppServer.Controllers
{
    public class ExerciseController : Controller
    {
        private IConfiguration _config;
        private IMapper _mapper;
        private ApplicationDBContext _context;

        public ExerciseController(IConfiguration config, IMapper mapper, ApplicationDBContext context)
        {
            _config = config;
            _mapper = mapper;
            _context = context;
        }
        [HttpGet]
        [Route("all")]
        public async Task<IActionResult> GetAllExercise()
        {
            return Ok(_context.Exercises.ToList());
        }


        [HttpGet]
        [Route("{id}")]
        public async Task<IActionResult> GetExerciseById([FromRoute] int id)
        {
            return Ok(null);
        }

        [HttpPost]
        public async Task<IActionResult> CreateExercise(ExerciseCreateDTO dto)
        {
            return Ok(null);
        }

        [HttpPut]
        [Route("{id}")]
        public async Task<IActionResult> UpdateExercise([FromRoute] int id, [FromBody] ExerciseCreateDTO dto)
        {
            return Ok(null);
        }

        [HttpDelete]
        [Route("{id}")]
        public async Task<IActionResult> DeleteExercise([FromRoute] int id)
        {
            return Ok(null);
        }
    }
}
