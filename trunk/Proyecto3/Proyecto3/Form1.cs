using System;
using System.Collections.Generic;
using System.ComponentModel;
using System.Data;
using System.Drawing;
using System.Linq;
using System.Text;
using System.Threading.Tasks;
using System.Windows.Forms;
using System.Collections;
using System.IO;

namespace Proyecto3
{
    public partial class GUI : Form
    {
        private Host host;
        private ArrayList lista;

        public GUI()
        {
            host = new Host(this);
            InitializeComponent();
        }

        private void button1_Click(object sender, EventArgs e)
        {
            descripcion.Text = host.direct();
        }

        private void panel3_Paint(object sender, PaintEventArgs e)
        {

        }

        private void actualizar_Click(object sender, EventArgs e)
        {
            int i = 0;
            lista=host.actualizarPlugins();
            foreach (FileInfo f in lista)
            {
                listaPlugins.Items.Add(f.Name);
            }
        }
    }
}
