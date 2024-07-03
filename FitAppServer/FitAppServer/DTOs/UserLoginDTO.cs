using FitAppServer.Models;
using System.ComponentModel.DataAnnotations;
using System.Text.Json.Serialization;

namespace FitAppServer.DTOs
{
    public class UserLoginDTO
    {
        [Required]
        [JsonPropertyName("username")]
        public string UserName { get; set; }
        [Required]
        [JsonPropertyName("password")]
        public string Password { get; set; }
    }
}
