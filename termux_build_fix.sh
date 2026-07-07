#!/bin/bash
# Bu script Termux'ta derleme yapmadan önce Boost header'larını hazırlar.
echo "Termux derleme hazırlığı başlatılıyor..."
cd 3party/boost
mkdir -p boost
echo "Boost header'ları birleştiriliyor (bu işlem biraz sürebilir)..."
for lib_dir in libs/*/include/boost; do
  [ -d "$lib_dir" ] && cp -rn "$lib_dir"/. boost/ 2>/dev/null || true
done
for lib_dir in libs/numeric/*/include/boost; do
  [ -d "$lib_dir" ] && cp -rn "$lib_dir"/. boost/ 2>/dev/null || true
done
echo "Boost header'ları hazır: $(ls boost/ | wc -l) adet"
cd ../..
echo "Hazırlık tamamlandı. Şimdi ./gradlew assembleFdroidDebug komutunu kullanabilirsiniz."
