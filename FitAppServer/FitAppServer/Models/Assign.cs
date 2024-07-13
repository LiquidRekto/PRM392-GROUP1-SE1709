using System.ComponentModel.DataAnnotations.Schema;

namespace FitAppServer.Models
{
    public class Assign
    {
        public int? TraineeId { get; set; }
        public int? TrainerId { get; set; }
        public DateTime AssignedDate { get; set; }
        public virtual User? Trainee { get; set; }
        public virtual User? Trainer { get; set; }
    }
}
