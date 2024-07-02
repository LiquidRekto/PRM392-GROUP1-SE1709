using Quartz;

namespace FitAppServer.CronJob
{
    public class ScheduleJob : IJob
    {
        public Task Execute(IJobExecutionContext context)
        {
            System.Diagnostics.Debug.WriteLine("The job is executing!");
            return Task.CompletedTask;
        }
    }
}
