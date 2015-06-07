using System;
using System.Collections.Generic;
using System.ComponentModel.DataAnnotations;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace Kappa.Data.Models
{
    public class Situation
    {
        public Situation()
        {
            this.Requests = new List<Request>();
        }
        public int Id { get; set; }

        public string Name { get; set; }

        public string Text { get; set; }

        public virtual ICollection<Request> Requests { get; set; }
    }
}
