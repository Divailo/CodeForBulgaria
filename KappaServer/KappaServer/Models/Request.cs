using System;
using System.Collections.Generic;
using System.Linq;
using System.Web;

namespace KappaServer.Models
{
    public class Request
    {
        public int Id { get; set; }

        public Guid CallerId { get; set; }
        
        public virtual ApplicationUser Caller { get; set; }

        public Emergency Emergency { get; set; }

        public int EmergencyId { get; set; }

        public string Message { get; set; }

    }
}