using System;
using System.Data;
using Serpis.Ad;
namespace CArticulo {
    public partial class ArticuloWindow : Gtk.Window {
        public ArticuloWindow(Articulo articulo) : base(Gtk.WindowType.Toplevel) {
            this.Build();
            entryNombre.Text = articulo.Nombre;
            spinPrecio.Value = (double)articulo.Precio;
            //comboCat.t = articulo.Categoria;

			saveAction.Activated += delegate {
                articulo.Nombre = entryNombre.Text;
                articulo.Precio = Decimal.Parse(spinPrecio.Text);
                ArticuloDao.Save(articulo);
				Destroy();
			};
        }
    }
}
