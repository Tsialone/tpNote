#!/bin/bash
set -e

echo "Building WAR..."
BACK_DIR="./backend"
FRONT_DIR="./frontend"

cd $BACK_DIR
mvn clean package
cd ../
npm run build --prefix $FRONT_DIR





WAR_FILE=$(ls $BACK_DIR/target/*.war | head -n 1)
echo "Copying WAR: $WAR_FILE to $DIST_DIR"

cp $WAR_FILE $DIST_DIR/tpNote.war
cp -r $FRONT_DIR/dist $DIST_DIR/


cp  -r $BACK_DIR/sql $DIST_DIR/



echo "Production package ready in $DIST_DIR/"
