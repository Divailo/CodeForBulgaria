using System.Collections.Generic;
using System.ComponentModel.DataAnnotations;

namespace Kappa.Data.Models
{
    public class Institution
    {
        public Institution()
        {
            this.Requests = new List<Request>();
        }
        public int Id { get; set; }

        public string Name { get; set; }

        public virtual ICollection<Request> Requests { get; set; }
    }
}
