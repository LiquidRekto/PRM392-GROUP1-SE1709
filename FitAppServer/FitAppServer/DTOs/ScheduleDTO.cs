using FitAppServer.Models;

namespace FitAppServer.DTOs
{
    public class ScheduleDTO
    {
        public int ScheduleId { get; set; }
        public DateTime StartTime { get; set; }
        public DateTime EndTime { get; set; }
        public UserDTO Trainer { get; set; }
        public UserDTO Trainee { get; set; }
    }
}
