using FitAppServer.CronJob.Jobs;
using FitAppServer.DTOs;
using FitAppServer.Service;
using FitAppServer.Utils;
using Microsoft.AspNetCore.Http;
using Microsoft.AspNetCore.Mvc;
using FitAppServer.Utils;
using FitAppServer.CronJob;

namespace FitAppServer.Controllers
{
    [Route("api/[controller]")]
    [ApiController]
    public class FCMTestController : ControllerBase
    {
        private readonly SchedulerService _schedulerService;

        public FCMTestController(SchedulerService schedulerService)
        {
            _schedulerService = schedulerService;
        }

        [HttpPost]
        public async Task<IActionResult> TestPushNotification([FromBody] FCMRequest fcmReq)
        {
            try
            {
                await FcmUtils.PushNotificationAsync(fcmReq.Title, fcmReq.Body, fcmReq.Token);
                return Ok("message sent");
            }
            catch (Exception ex)
            {
                return BadRequest(ex);
            }
        }

        [HttpPost]
        [Route("schedule")]
        public async Task<IActionResult> TestSchedule([FromBody] JobRequest req)
        {
            try
            {
                await _schedulerService.ScheduleJob<ScheduleJob>(req, req.DateToTrain.Subtract(TimeSpan.FromMinutes(20)), MISC.GenerateRandomString(32), MISC.GenerateRandomString(32));
                return Ok("message sent");
            }
            catch (Exception ex)
            {
                return BadRequest(ex);
            }
        }
    }
}
