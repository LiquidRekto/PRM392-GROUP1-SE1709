using FitAppServer.Models;

namespace FitAppServer.DTOs
{
    public class ScheduleDTO
    {
        public int ScheduleId { get; set; }
        public int TraineeId { get; set; }
        public int TrainerId { get; set; }
        public DateTime StartTime { get; set; }
        public DateTime EndTime { get; set; }
        public string TraineeFullName { get; set; }
        public string TrainerFullName { get; set; }
    }
}
