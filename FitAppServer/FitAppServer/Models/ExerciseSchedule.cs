namespace FitAppServer.Models
{
    public class ExerciseSchedule
    {
        public int ExerciseId { get; set; }
        public int ScheduleId { get; set; }
        public Exercise? Exercise { get; set; }
        public Schedule? Schedule { get; set; }
    }
}
