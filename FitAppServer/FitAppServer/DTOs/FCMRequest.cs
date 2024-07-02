using System.Text.Json.Serialization;

namespace FitAppServer.DTOs
{
    public class FCMRequest
    {
        [JsonPropertyName("title")]
        public string Title { get; set; }
        [JsonPropertyName("body")]
        public string Body { get; set; }
        [JsonPropertyName("token")]
        public string Token { get; set; }
    }
}
