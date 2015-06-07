using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Web;
using System.Web.Http;
using Kappa.Data;
using Kappa.Data.Models;
using Kappa.RestServices.Models;

namespace Kappa.RestServices.Controllers
{
    public class RequestController : ApiController
    {
        KappaContext db = new KappaContext();

        // POST api/request
        public IHttpActionResult Post(RequestInputModel requestInput)
        {
            HttpContext.Current.Response.AppendHeader("Access-Control-Allow-Origin", "*");
            var situations = db.Situations;
            var message = new StringBuilder();
            var request = new Request
            {
                EmergencyType = (EmergencyType)Enum.Parse(typeof(EmergencyType), requestInput.EmergencyType),
                LocationX = requestInput.LocationX,
                LocationY = requestInput.LocationY,
                Message = requestInput.Message
            };

            message.AppendLine("Keep calm and...");

            foreach (var situation in requestInput.Situations)
            {
                var currentSituation = situations.FirstOrDefault(s => s.Name == situation);
                request.Situations.Add(currentSituation);
                if (currentSituation != null)
                {
                    message.AppendFormat("- {0}\n", currentSituation.Text);
                }
            }

            db.Requests.Add(request);

            db.SaveChanges();

            return Ok(
                new
                {
                    Message = message.ToString()
                });
        }
    }
}