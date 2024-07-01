using FitAppServer.Utils;
using Microsoft.AspNetCore.Http;
using Microsoft.AspNetCore.Mvc;

namespace FitAppServer.Controllers
{
    [Route("api/[controller]")]
    [ApiController]
    public class FCMTestController : ControllerBase
    {
        [HttpPost]
        public async Task<IActionResult> TestPushNotification([FromQuery] string title, [FromQuery] string body, [FromQuery] string token)
        {
            try
            {
                await FcmUtils.PushNotificationAsync(title, body, token);
                return Ok("message sent");
            }
            catch (Exception ex)
            {
                return BadRequest(ex);
            }
        }
    }
}
