using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;
using System.IO;
using System.Collections;
using System.Reflection;
using Plugin;

namespace Proyecto3
{
    class Host
    {
        private List<IPlugin> plugins;
        private GUI gui;

        public Host(GUI gui)
        {
            plugins = new List<IPlugin>();
            this.gui = gui;
        }

        public List<IPlugin> actualizarPlugins()
        {
            plugins = new List<IPlugin>();
            AppDomain domain = AppDomain.CreateDomain("pluginDomain");
            foreach (string fileOn in Directory.GetFiles("plugins"))
            {
                FileInfo file = new FileInfo(fileOn);
                if (file.Extension == ".dll")
                    cargarPlugin(fileOn, domain);
            }
            return plugins;
        }

        private void cargarPlugin(String fileOn, AppDomain domain)
        {
            AssemblyName assemblyName = AssemblyName.GetAssemblyName(fileOn);
            Assembly ass = domain.Load(assemblyName);

            System.Type[] types = ass.GetTypes();
            var tipoIPlugin = typeof(IPlugin);
            foreach (Type t in types)
            {
                IPlugin p = (IPlugin)Activator.CreateInstance(t);
                plugins.Add(p);
            }
        }
    }
}
