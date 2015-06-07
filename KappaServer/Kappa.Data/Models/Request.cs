using System;
using System.Collections.Generic;
using System.ComponentModel.DataAnnotations;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace Kappa.Data.Models
{
    public class Request
    {
        public Request()
        {
            this.Institutions = new List<Institution>();
            this.Situations = new List<Situation>();
        }
        
        public int Id { get; set; }

        public EmergencyType EmergencyType { get; set; }

        public double LocationX { get; set; }

        public double LocationY { get; set; }

        public ICollection<Situation> Situations { get; set; }

        public ICollection<Institution> Institutions { get; set; }

        public string Message { get; set; }
    }
}
