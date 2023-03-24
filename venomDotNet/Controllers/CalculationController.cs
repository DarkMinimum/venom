using System.Diagnostics;
using Microsoft.AspNetCore.Mvc;
using org.matheval;

namespace VenomDotNet.Controllers;

[ApiController]
[Route("")]
public class CalculationController : ControllerBase
{
    private readonly ILogger<CalculationController> _logger;

    public CalculationController(ILogger<CalculationController> logger)
    {
        _logger = logger;
    }

    [HttpPost]
    [Consumes("text/plain")]
    public string Get([FromBody] string stringExpression)
    {
        try
        {
            var ex = Uri.UnescapeDataString(stringExpression).Split("=")[1];
            var sw = new Stopwatch();
            sw.Start();
            var result = new Expression(ex).Eval().ToString();
            sw.Stop();
            return result + " t: " + sw.Elapsed.Milliseconds + " ms";
        }
        catch (Exception ex)
        {
            return "wrong expression";
        }
    }
}