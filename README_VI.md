# Hải Trình Huyền Thoại v1.5 — dự án Android WebView

Đây vẫn là **mã nguồn**, chưa phải APK cài đặt. Dự án đã kèm mã game và workflow tự build APK trên GitHub Actions.

## Build APK tự động
1. Giải nén ZIP này.
2. Tạo một repository GitHub mới và tải toàn bộ nội dung thư mục `HaiTrinhHuyenThoai_WebView_ApkProject` lên repository (bao gồm thư mục `.github`).
3. Mở tab **Actions**, chọn **Build Android APK**, rồi bấm **Run workflow** (hoặc đợi workflow chạy sau khi push).
4. Mở lần chạy hoàn tất, kéo xuống **Artifacts**, tải `Hai-Trinh-Huyen-Thoai-v1.5-debug-apk.zip` và giải nén để lấy `app-debug.apk`.

## Chạy game
APK hiện mở `http://127.0.0.1:8080/`. Nghĩa là server game phải chạy trên cùng điện thoại. Sau khi cài Termux, cần cài Node.js, giải nén thư mục `game-files/hai_trinh_huyen_thoai_v14/server`, vào thư mục đó và chạy `node server.js`. Giữ Termux hoạt động rồi mở APK.

Nếu chỉ cài APK mà không chạy server, game sẽ không tải được. Đây là bản debug/thử nghiệm, chưa phải bản phát hành Play Store.
