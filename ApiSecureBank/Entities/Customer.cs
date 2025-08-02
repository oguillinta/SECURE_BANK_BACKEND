namespace ApiSecureBank.Entities
{
    public class Customer
    {
        public int Id { get; set; }
        public String customerId { get; set; } = null!;
        public String accountNumber { get; set; } = null!;
        public String accountType { get; set; } = null!;
        public Double balance { get; set; }
        public Double dailyTransactionLimit { get; set; }
        public String? status { get; set; }
        public DateTime createdAt { get; set; }
        public DateTime updatedAt { get; set; }
    }
}
