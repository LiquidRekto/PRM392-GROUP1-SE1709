using FitAppServer.CronJob.Jobs;
using Quartz;
using Quartz.Spi;

namespace FitAppServer.CronJob
{
    public class ScheduleJobFactory : IJobFactory
    {
        private readonly IServiceProvider _serviceProvider;

        public ScheduleJobFactory(IServiceProvider serviceProvider)
        {
            _serviceProvider = serviceProvider;
        }

        public IJob NewJob(TriggerFiredBundle bundle, IScheduler scheduler)
        {
            var jobDetail = bundle.JobDetail;
            var jobType = jobDetail.JobType;


            // Handle other job types or throw an exception if needed
            throw new NotImplementedException($"Job of type {jobType} is not supported by the custom job factory.");
        }

        public void ReturnJob(IJob job)
        {
            // Cleanup if necessary
        }
    }
}
