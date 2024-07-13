using System.ComponentModel.DataAnnotations.Schema;
using System.ComponentModel.DataAnnotations;

namespace FitAppServer.Models
{
    public class User
    {
        [Key, DatabaseGenerated(DatabaseGeneratedOption.Identity)]
        public int UserId { get; set; }

        public string UserName { get; set; }

        public string Password {  get; set; }

        public string FirstName { get; set; }
        public string LastName { get; set; }
        public string Email { get; set; }

        public int UserRoleId { get; set; }
        public virtual UserRole? UserRole { get; set; }

        public virtual ICollection<Assign> TrainerAssignments { get; set; } // if the user is a trainer
        public virtual Assign TraineeAssignment { get; set; } // if the user is a trainee

        public virtual ICollection<Schedule>? TrainerSchedules { get; set; } // if the user is a trainer
        public virtual ICollection<Schedule>? TraineeSchedules { get; set; } // if the user is a trainee
    }
}
