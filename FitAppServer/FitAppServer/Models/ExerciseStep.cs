using System.ComponentModel.DataAnnotations.Schema;
using System.ComponentModel.DataAnnotations;

namespace FitAppServer.Models
{
    public class ExerciseStep
    {
        [Key, DatabaseGenerated(DatabaseGeneratedOption.Identity)]
        public int ExerciseStepId { get; set; }
        public int ExerciseId { get; set; }
        public int StepIndex { get; set; }
        public string? MediaUrl { get; set; }
        public string? StepDescription { get; set; }
        public Exercise? Exercise { get; set; }
    }
}
