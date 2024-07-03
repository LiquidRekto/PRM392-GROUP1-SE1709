using System.ComponentModel.DataAnnotations.Schema;
using System.ComponentModel.DataAnnotations;

namespace FitAppServer.Models
{
    public class FCMToken
    {
        [Key, DatabaseGenerated(DatabaseGeneratedOption.Identity)]
        public int TokenId { get; set; }
        public string TokenString { get; set; }
        public DateTime ValidUntil { get; set; }
    }
}
