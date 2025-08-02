namespace ApiSecureBank.Entities
{
    public class Account
    {
        public int Id { get; set; }
        public String Name { get; set; } = null!;
        public double CurrentBalance { get; set; }
        public double DailyLimit { get; set; }
        public Boolean Active { get; set; }
        public DateTime Opened { get; set; }
        public DateTime LastUpdated { get; set; }
        public int TypeAccountId { get; set; }
        public int CustomerId { get; set; }
    }
}
