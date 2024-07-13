namespace FitAppServer.Models
{
    public class ExerciseSchedule
    {
        public int ExerciseId { get; set; }
        public int ScheduleId { get; set; }
        public virtual Exercise? Exercise { get; set; }
        public virtual Schedule? Schedule { get; set; }
    }
}
