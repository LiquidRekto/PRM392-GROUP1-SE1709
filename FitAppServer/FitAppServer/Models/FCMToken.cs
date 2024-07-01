namespace FitAppServer.Models
{
    public class FCMToken
    {
        public int TokenId { get; set; }
        public string TokenString { get; set; }
        public DateTime ValidUntil { get; set; }
    }
}
