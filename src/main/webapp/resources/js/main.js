$(".btn-delete-user").click(function (){
    if (!confirm("Bạn có chắc chắn muốn xóa người dùng này?")) {
        return;
    }

    const idUser = $(this).attr("data");
    // su dung ajax
    $.ajax({
        url: `/api/v1/users/${idUser}`,
        method: "DELETE",
        success: function (res) {
            // xu ly response tu phia server tra ve
            // dung jquery de xoa phan tu tr tuong ung
            $(`tr[id="${idUser}"]`).remove();
            toastr.success(res);
        },
        // xu ly goi ajax that bai
        error: function (err) {
            console.log(err);
        }
    })
})