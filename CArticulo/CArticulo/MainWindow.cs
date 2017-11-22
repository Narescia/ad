using System;
using Gtk;
using MySql.Data.MySqlClient;
using System.Data;
using Serpis.Ad;
using CArticulo;

public partial class MainWindow : Gtk.Window {
    public MainWindow() : base(Gtk.WindowType.Toplevel) {
		Build();
		Title = "Artículo";

		deleteAction.Sensitive = false;
		editAction.Sensitive = false;

		App.Instance.Connection = new MySqlConnection("server=localhost;database=dbprueba;user=root;password=sistemas");
		App.Instance.Connection.Open();

		TreeViewHelper.Fill(treeView, ArticuloDao.SelectAll);
        //select a.id, a.nombre, precio, c.nombre as categoria from articulo a left
        //join categoria c on a.categoria = c.id order by a.id);


		treeView.Selection.Changed += delegate {
			bool hasSelected = treeView.Selection.CountSelectedRows() > 0;
			deleteAction.Sensitive = hasSelected;
			editAction.Sensitive = hasSelected;
		};

		newAction.Activated += delegate {
            Articulo articulo = new Articulo();
			new ArticuloWindow(articulo);
		};

		editAction.Activated += delegate {
			object id = TreeViewHelper.GetId(treeView);
			Articulo articulo = ArticuloDao.Load(id);
			new ArticuloWindow(articulo);
		};

		refreshAction.Activated += delegate {
			TreeViewHelper.Fill(treeView, ArticuloDao.SelectAll);
		};

		deleteAction.Activated += delegate {
			if (WindowHelper.Confirm(this, "¿Quieres eliminar el registro?")) {
				object id = TreeViewHelper.GetId(treeView);
				ArticuloDao.Delete(id);
			}
		};
	}

	protected void OnDeleteEvent(object sender, DeleteEventArgs a) {
		App.Instance.Connection.Close();
		Application.Quit();
		a.RetVal = true;
	}
}
