using System.ComponentModel.DataAnnotations.Schema;
using System.ComponentModel.DataAnnotations;

namespace FitAppServer.Models
{
    public class Exercise
    {
        [Key, DatabaseGenerated(DatabaseGeneratedOption.Identity)]
        public int ExerciseId { get; set; }
        public string? ExerciseName { get; set; }
        public string? ExerciseDescription { get; set; }
        public string? Thumbnail {  get; set; }
        public virtual ICollection<Schedule> Schedules { get; set; }

        public virtual ICollection<ExerciseStep> ExerciseSteps { get; set; }
    }
}
