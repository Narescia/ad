using System;
namespace CCategoria
{
    public partial class TreeViewHelper : Gtk.Window
    {
        public TreeViewHelper() :
                base(Gtk.WindowType.Toplevel)
        {
            this.Build();
        }
    }
}
