using System;
using System.Data;
using Serpis.Ad;
using Gtk;
namespace CArticulo {
    public partial class ArticuloWindow : Gtk.Window {
        public ArticuloWindow(Articulo articulo) : base(Gtk.WindowType.Toplevel) {
            this.Build();
            entryNombre.Text = articulo.Nombre;
            spinPrecio.Value = (double)articulo.Precio;
            ComboBoxHelper.Fill(comboBox, "select id, nombre from articulo order by id", 0);

			saveAction.Activated += delegate {
                articulo.Nombre = entryNombre.Text;
                articulo.Precio = Decimal.Parse(spinPrecio.Text);
                articulo.Categoria = long.Parse(ComboBoxHelper.GetId(comboBox).ToString());
                ArticuloDao.Save(articulo);
				Destroy();
			};
        }
    }
}
