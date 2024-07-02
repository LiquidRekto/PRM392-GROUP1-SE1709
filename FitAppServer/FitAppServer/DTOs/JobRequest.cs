using System.Text.Json.Serialization;

namespace FitAppServer.DTOs
{
    public class JobRequest
    {
        [JsonPropertyName("dateToTrain")]
        public DateTime DateToTrain { get; set; }
        [JsonPropertyName("trainerName")]
        public string TrainerName { get; set; }
        [JsonPropertyName("token")]
        public string Token { get; set; }
    }
}
