using Kappa.Data;
using Kappa.Data.Models;

namespace Kappa.RestServices.Models
{
    public class RequestInputModel
    {
        public int CallerId { get; set; }

        public string EmergencyType { get; set; }

        public double LocationX { get; set; }

        public double LocationY { get; set; }

        public string[] Situations { get; set; }

        public string Message { get; set; }
    }
}