using Quartz.Impl;
using Quartz;
using static Quartz.Logging.OperationName;
using FitAppServer.DTOs;
using FitAppServer.CronJob.Jobs;

namespace FitAppServer.Service
{
    public class SchedulerService
    {
        private readonly IScheduler _scheduler;

        public SchedulerService()
        {
            ISchedulerFactory schedulerFactory = new StdSchedulerFactory();
            _scheduler = schedulerFactory.GetScheduler().Result;
        }

        public async Task ScheduleJob<TJob>(JobRequest req, DateTime dateTime, string jobName, string groupName) where TJob : IJob
        {
            System.Diagnostics.Debug.WriteLine("JOB SCHEDULE STARTED!");
            var jobKey = new JobKey(jobName, groupName);
            var job = JobBuilder.Create<NotifyTraineeJob>()
                .WithIdentity(jobKey)
                .UsingJobData("TrainerName", req.TrainerName)
                .UsingJobData("DateToTrain", req.DateToTrain.ToString())
                .UsingJobData("Token", req.Token)
                .Build();

            var trigger = TriggerBuilder.Create()
                .WithIdentity($"{jobName}Trigger", groupName)
                .StartAt(dateTime)
                .ForJob(jobKey)
                .Build();

            System.Diagnostics.Debug.WriteLine("JOB WILL TRIGGER AT: " + trigger.StartTimeUtc.ToString());
            System.Diagnostics.Debug.WriteLine("INPUTTED DATA: " + trigger.JobDataMap.ToString());

            await _scheduler.ScheduleJob(job, trigger);
            await StartAsync();
        }
        public async Task StartAsync()
        {
            await _scheduler.Start();
        }
        public async Task StopAsync()
        {
            await _scheduler.Shutdown();
        }
    }
}
