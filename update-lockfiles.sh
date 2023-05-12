#!/bin/bash

# A shell script to update lockfiles for all dependencies

# Print message to remove all lockfiles
echo "Removing all lockfiles..."

# Find all lockfiles and delete them
find . -name '*.lockfile' -delete

# Print message to resolve dependencies and create new lockfiles
echo "Resolving all dependencies and writing new lockfiles..."

# Run gradle task to resolve dependencies and write new lockfiles
./gradlew resolveAllDependencies --write-locks >/dev/null

# Print message to indicate lockfile update is complete
echo "Lockfile update complete"
