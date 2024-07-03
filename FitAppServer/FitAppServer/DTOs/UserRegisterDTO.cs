using System.ComponentModel.DataAnnotations;
using System.Text.Json.Serialization;

namespace FitAppServer.DTOs
{
    public class UserRegisterDTO
    {
        [Required]
        [JsonPropertyName("username")]
        public string UserName { get; set; }
        [Required]
        [JsonPropertyName("password")]
        public string Password { get; set; }
        [Required]
        [JsonPropertyName("firstName")]
        public string FirstName { get; set; }
        [Required]
        [JsonPropertyName("lastName")]
        public string LastName { get; set; }
        [Required]
        [JsonPropertyName("email")]
        public string Email { get; set; }
        [Required]
        [JsonPropertyName("userRoleId")]

        public int UserRoleId { get; set; }
    }
}
