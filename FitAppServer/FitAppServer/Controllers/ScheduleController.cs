using AutoMapper;
using FitAppServer.DTOs;
using FitAppServer.Models;
using Microsoft.AspNetCore.Http;
using Microsoft.AspNetCore.Mvc;
using Microsoft.EntityFrameworkCore;
using System.Globalization;

namespace FitAppServer.Controllers
{
    [Route("api/[controller]")]
    [ApiController]
    public class ScheduleController : ControllerBase
    {
        private IConfiguration _config;
        private IMapper _mapper;
        private ApplicationDBContext _context;

        public ScheduleController(IConfiguration config, IMapper mapper, ApplicationDBContext context)
        {
            _config = config;
            _mapper = mapper;
            _context = context;
        }

        [HttpGet]
        [Route("all")]
        public async Task<IActionResult> GetAllSchedule()
        {
            return Ok(_context.Schedules.ToList());
        }

        [HttpGet]
        [Route("byUser/{user}/date/{date}")]
        public async Task<IActionResult> GetScheduleByUserDate([FromRoute] int user, [FromRoute] string date)
        {
            List<Schedule> sc = new List<Schedule>();
            DateTime startDate = DateTime.ParseExact(date, "yyyy-MM-dd", CultureInfo.InvariantCulture);
            DateTime endDate = startDate.AddDays(1);

            sc = _context.Schedules.Include(s => s.Trainer).Where(s => (s.TraineeId == user || s.TrainerId== user)
             && s.StartTime >= startDate && s.StartTime <= endDate).OrderBy(w => w.StartTime)
                .ToList();

            List<ScheduleDTO> result = sc.Select(w => new ScheduleDTO
            {
                ScheduleId = w.ScheduleId,
                StartTime= w.StartTime,
                EndTime= w.EndTime,
                Trainer = new UserDTO
                {
                    UserId = w.Trainer.UserId,
                    FirstName = w.Trainer.FirstName,
                    LastName = w.Trainer.LastName
                }
            }).ToList();
            return Ok(result);
        }

        [HttpGet]
        [Route("{id}")]
        public async Task<IActionResult> GetScheduleById([FromRoute] int id)
        {
            return Ok(null);
        }

        [HttpPost]
        public async Task<IActionResult> CreateSchedule(ScheduleCreateDTO dto)
        {
            return Ok(null);
        }

        [HttpPut]
        [Route("{id}")]
        public async Task<IActionResult> UpdateSchedule([FromRoute] int id, [FromBody] ScheduleCreateDTO dto)
        {
            return Ok(null);
        }

        [HttpDelete]
        [Route("{id}")]
        public async Task<IActionResult> DeleteSchedule([FromRoute] int id)
        {
            return Ok(null);
        }
    }
}
