using System.Text;

namespace FitAppServer.Utils
{
    public class MISC
    {
        public static string GenerateRandomString(int length)
        {
            const string validCharacters = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ1234567890";
            StringBuilder result = new StringBuilder(length);
            Random random = new Random();

            for (int i = 0; i < length; i++)
            {
                result.Append(validCharacters[random.Next(validCharacters.Length)]);
            }

            return result.ToString();
        }
    }
}
