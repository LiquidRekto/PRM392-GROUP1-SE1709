using FitAppServer.FCM;
using Google.Apis.FirebaseCloudMessaging.v1.Data;

namespace FitAppServer.Utils
{
    public class FcmUtils
    {
        public static async Task PushNotificationAsync(string title, string body, string token)
        {
            System.Diagnostics.Debug.WriteLine("BEGIN PUSHING NOTIFICATION!");
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
                var result = await fcmService.Projects.Messages.Send(msgRequest, "projects/fitapp-project").ExecuteAsync();
                System.Diagnostics.Debug.WriteLine($"Message sent successfully: {result.Name}");
            }
            catch (Exception ex)
            {
                throw new Exception($"Error sending message: {ex.Message}");
            }
        }
    }
}
