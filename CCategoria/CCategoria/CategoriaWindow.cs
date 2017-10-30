using System;
using System.Data;
using Serpis.Ad;
namespace CCategoria {
    public partial class CategoriaWindow : Gtk.Window {
        object id;
        public CategoriaWindow(object id) : base(Gtk.WindowType.Toplevel) {
            this.Build();

            this.id = id;

            Categoria categoria = CategoriaDao.Load(id);
            entryNombre.Text = categoria.Nombre;

            saveAction.Activated += delegate {
                categoria.Nombre = entryNombre.Text;
                CategoriaDao.Save(categoria);
                Destroy();
            };
        }

        public CategoriaWindow() : base(Gtk.WindowType.Toplevel) {
            this.Build();
            Categoria categoria = new Categoria();
            saveAction.Activated += delegate {
                categoria.Nombre = entryNombre.Text;
                CategoriaDao.Save(categoria);
                Destroy();
            };
        }
    }
}
