using FitAppServer.FCM;
using Google.Apis.FirebaseCloudMessaging.v1.Data;

namespace FitAppServer.Utils
{
    public class FcmUtils
    {
        public static async Task PushNotificationAsync(string title, string body, string token)
        {
            var fcmService = FcmNotificationSender.GetFcmService();

            

            var message = new Message
            {
                Token = token,
                Notification = new Notification
                {
                    Title = title,
                    Body = body
                }
            };

            SendMessageRequest msgRequest = new SendMessageRequest
            {
                Message = message
            };

            try
            {
                var result = await fcmService.Projects.Messages.Send(msgRequest, null).ExecuteAsync();
                Console.WriteLine($"Message sent successfully: {result.Name}");
            }
            catch (Exception ex)
            {
                Console.WriteLine($"Error sending message: {ex.Message}");
            }
        }
    }
}
