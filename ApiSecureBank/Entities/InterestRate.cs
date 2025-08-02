namespace ApiSecureBank.Entities
{
    public class InterestRate
    {
        public int Id { get; set; }
        public String productType { get; set; } = null!;
        public String productName { get; set; } = null!;
        public Double rate { get; set; }
        public String term { get; set; } = null!;
        public Double minimumAmount { get; set; }
        public Double maximumAmount { get; set; }
        public String? description { get; set; } 
        public Boolean status { get; set; }
        public DateTime effectiveDate { get; set; } 
        public DateTime updatedAt { get; set; }
    }
}
