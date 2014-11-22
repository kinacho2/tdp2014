using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace Plugin
{
    public class PluginDivision : IPlugin
    {
        public string Name { get { return "Division"; } }
        public string Descripcion { get { return "Operacion que permite dividir dos numeros reales. No capture la excepcion de dividir por cero porque la verdad que me resulta muy gracioso como lo resuelve C#"; } }

        public double calcular(double a, double b)
        {
            return a / b;
        }
    }

}