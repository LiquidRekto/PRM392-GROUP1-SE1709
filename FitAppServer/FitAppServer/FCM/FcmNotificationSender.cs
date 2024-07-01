using Google.Apis.Auth.OAuth2;
using Google.Apis.FirebaseCloudMessaging.v1;
using Google.Apis.Services;

namespace FitAppServer.FCM
{
    public class FcmNotificationSender
    {
        private readonly IConfiguration _configuration;

        public FcmNotificationSender(IConfiguration configuration)
        {
            _configuration = configuration;
            CredentialPath = _configuration["CREDENTIAL_PATH"];
        }

        private static readonly string[] Scopes = { FirebaseCloudMessagingService.Scope.CloudPlatform };
        private static readonly string ApplicationName = "FitApp";
        private static string CredentialPath;

        public static FirebaseCloudMessagingService GetFcmService()
        {
            GoogleCredential credential;
            using (var stream = new FileStream(CredentialPath, FileMode.Open, FileAccess.Read))
            {
                credential = GoogleCredential.FromStream(stream)
                    .CreateScoped(Scopes);
            }

            return new FirebaseCloudMessagingService(new BaseClientService.Initializer
            {
                HttpClientInitializer = credential,
                ApplicationName = ApplicationName,
            });
        }
    }
}
