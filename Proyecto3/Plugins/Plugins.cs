﻿using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace Plugin
{
    using System;
    using System.Collections.Generic;
    using System.Linq;
    using System.Text;
    using System.Threading.Tasks;

    namespace Plugin
    {
        

        class PluginResta : IPlugin
        {
            public string Name { get { return "Resta"; } }
            public string Descripcion { get { return "Operacion que permite restar algebraicamente dos numeros reales."; } }

            public double calcular(double a, double b)
            {
                return a - b;
            }
        }

        