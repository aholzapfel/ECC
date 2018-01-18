#!/bin/bash
ELEV_BIN_DIR="$( cd "$( dirname "${BASH_SOURCE[0]}" )" && pwd )"
ELEV_HOME="${ELEV_BIN_DIR}/.."
CLASS_PATH="lib/*"

OLD_CD="$PWD"
cd "$ELEV_HOME"
java -cp "${CLASS_PATH}" elevator.Challenge "$@"
cd "$OLD_CD"
