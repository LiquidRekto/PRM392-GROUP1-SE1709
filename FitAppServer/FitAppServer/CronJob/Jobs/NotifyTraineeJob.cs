using FitAppServer.Utils;
using Quartz;

namespace FitAppServer.CronJob.Jobs
{
    public class NotifyTraineeJob : IJob
    {
        public string NotificationTitle {  get; set; }
        public string NotificationContent { get; set; }

        public string NotificationToken { get; set; }

        public Task Execute(IJobExecutionContext context)
        {
            System.Diagnostics.Debug.WriteLine("BEGIN EXECUTING JOB!");
            JobDataMap dataMap = context.JobDetail.JobDataMap;
            // job params: TrainerName, DateToTrain, Token
            string trainerName = dataMap.GetString("TrainerName");
            DateTime dateToTrain = dataMap.GetDateTime("DateToTrain");
            NotificationTitle = "Hey! Your training session is about to begin!";
            NotificationContent = $"Your session with trainer \"{trainerName}\" will begin at: {dateToTrain.ToString("HH:mm, d MMMM, yyyy")}";
            NotificationToken = dataMap.GetString("Token");
            FcmUtils.PushNotificationAsync(NotificationTitle, NotificationContent, NotificationToken);
            return Task.CompletedTask;
        }
    }
}
