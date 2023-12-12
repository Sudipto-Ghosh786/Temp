cd your_folder_path
find . -name "*.java" -exec sed -i 's/class \([a-zA-Z0-9_]*\)/class Title_\1/g' {} \;
cd your_folder_path
find . -name "*.java" -exec sed -i 's/^import .*;/import xyz;/g' {} \;

cd your_folder_path
find . -name "*.java" -exec sed -i '/^io.swagger/d' {} \;
