using FitAppServer.Models;

namespace FitAppServer.DTOs
{
    public class ScheduleCreateDTO
    {
        public int TrainerId { get; set; }
        public DateTime StartTime { get; set; }
        public DateTime EndTime { get; set; }
        public User Trainee { get; set; }
        public User Trainer { get; set; }
    }
}
