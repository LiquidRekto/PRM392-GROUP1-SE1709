using FitAppServer.DTOs;
using Microsoft.AspNetCore.Http;
using Microsoft.AspNetCore.Mvc;

namespace FitAppServer.Controllers
{
    [Route("api/[controller]")]
    [ApiController]
    public class ScheduleController : ControllerBase
    {
        [HttpGet]
        [Route("all")]
        public async Task<IActionResult> GetAllSchedule()
        {
            return Ok(null);
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
