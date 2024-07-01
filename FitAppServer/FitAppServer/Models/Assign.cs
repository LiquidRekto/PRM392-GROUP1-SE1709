namespace FitAppServer.Models
{
    public class Assign
    {
        public int TraineeId { get; set; }
        public int TrainerId { get; set; }
        public DateTime AssignedDate { get; set; }
        public User? Trainee { get; set; }
        public User? Trainer { get; set; }
    }
}
